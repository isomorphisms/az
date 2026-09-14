package org.isomorphisms.az.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class SearchActivity extends Activity {
    public static final String EXTRA_RESULTS_TSV = "org.isomorphisms.az.SEARCH_RESULTS_TSV";
    public static final String EXTRA_QUERY = "org.isomorphisms.az.SEARCH_QUERY";

    private static final int BG = Color.rgb(20, 18, 24);
    private static final int SURFACE = Color.rgb(33, 31, 38);
    private static final int SURFACE_HIGH = Color.rgb(43, 41, 48);
    private static final int PRIMARY = Color.rgb(208, 188, 255);
    private static final int ON_PRIMARY = Color.rgb(56, 30, 114);
    private static final int TEXT = Color.rgb(230, 224, 233);
    private static final int MUTED = Color.rgb(202, 196, 208);
    private static final int OUTLINE = Color.rgb(147, 143, 153);

    private EditText query;
    private TextView status;
    private LinearLayout results;
    private List<SearchResults.Item> source = Collections.emptyList();

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        setContentView(screen());

        Intent intent = getIntent();
        String suppliedQuery = intent.getStringExtra(EXTRA_QUERY);
        if (suppliedQuery != null) {
            query.setText(suppliedQuery);
        }

        String supplied = suppliedTsv(intent);
        if (supplied != null && !supplied.isEmpty()) {
            load(supplied);
        } else {
            render(Collections.emptyList(),
                    "No results loaded · run the external az search handoff from Termux");
        }
    }

    private String suppliedTsv(Intent intent) {
        String supplied = intent.getStringExtra(EXTRA_RESULTS_TSV);
        if ((supplied == null || supplied.isEmpty())
                && Intent.ACTION_SEND.equals(intent.getAction())
                && "text/plain".equals(intent.getType())) {
            supplied = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        return supplied;
    }

    private View screen() {
        LinearLayout root = column();
        root.setBackgroundColor(BG);

        TextView title = text("AZ", 28, TEXT);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setPadding(dp(20), dp(18), dp(20), dp(2));
        root.addView(title, matchWrap());

        TextView subtitle = text("Amazon search results", 13, MUTED);
        subtitle.setPadding(dp(20), 0, dp(20), dp(12));
        root.addView(subtitle, matchWrap());

        LinearLayout search = new LinearLayout(this);
        search.setGravity(Gravity.CENTER_VERTICAL);
        search.setPadding(dp(18), dp(6), dp(6), dp(6));
        search.setMinimumHeight(dp(60));
        search.setBackground(box(SURFACE_HIGH, 30, OUTLINE));

        query = new EditText(this);
        query.setSingleLine(true);
        query.setHint("Filter loaded results");
        query.setHintTextColor(MUTED);
        query.setTextColor(TEXT);
        query.setTextSize(16);
        query.setBackgroundColor(Color.TRANSPARENT);
        query.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        query.setOnEditorActionListener((v, action, event) -> {
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                filter();
                return true;
            }
            return false;
        });
        search.addView(query, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1));

        TextView go = button("Filter", PRIMARY, ON_PRIMARY);
        go.setOnClickListener(v -> filter());
        search.addView(go);

        LinearLayout.LayoutParams searchLayout = matchWrap();
        searchLayout.setMargins(dp(16), 0, dp(16), dp(12));
        root.addView(search, searchLayout);

        status = text("", 12, MUTED);
        status.setPadding(dp(20), 0, dp(20), dp(10));
        root.addView(status, matchWrap());

        ScrollView scroll = new ScrollView(this);
        results = column();
        results.setPadding(dp(16), 0, dp(16), dp(24));
        scroll.addView(results, matchWrap());
        root.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        return root;
    }

    private void load(String tsv) {
        try {
            source = SearchResults.parseTsv(tsv);
            render(source, source.size() + " results from external az search");
        } catch (IllegalArgumentException error) {
            source = Collections.emptyList();
            render(source, "Could not parse external az search results");
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void filter() {
        String needle = query.getText().toString().trim().toLowerCase(Locale.ROOT);
        if (needle.isEmpty()) {
            render(source, source.size() + " loaded az search results");
            return;
        }
        ArrayList<SearchResults.Item> matches = new ArrayList<>();
        for (SearchResults.Item item : source) {
            if (item.title.toLowerCase(Locale.ROOT).contains(needle)
                    || item.asin.toLowerCase(Locale.ROOT).contains(needle)) {
                matches.add(item);
            }
        }
        render(matches, matches.size() + " matches in loaded az search results");
    }

    private void render(List<SearchResults.Item> items, String label) {
        status.setText(label);
        results.removeAllViews();
        if (items.isEmpty()) {
            TextView empty = text("No products to show", 16, MUTED);
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(0, dp(56), 0, dp(56));
            results.addView(empty, matchWrap());
            return;
        }
        for (SearchResults.Item item : items) {
            LinearLayout.LayoutParams layout = matchWrap();
            layout.bottomMargin = dp(12);
            results.addView(card(item), layout);
        }
    }

    private View card(SearchResults.Item item) {
        LinearLayout card = column();
        card.setPadding(dp(18), dp(17), dp(18), dp(16));
        card.setBackground(box(SURFACE, 20, -1));
        card.setElevation(dp(1));

        TextView name = text(item.title.isEmpty() ? item.asin : item.title, 17, TEXT);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        card.addView(name, matchWrap());

        TextView price = text(price(item), 21, PRIMARY);
        price.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        LinearLayout.LayoutParams priceLayout = matchWrap();
        priceLayout.topMargin = dp(12);
        card.addView(price, priceLayout);

        TextView asin = text(item.asin, 12, MUTED);
        LinearLayout.LayoutParams asinLayout = matchWrap();
        asinLayout.topMargin = dp(5);
        card.addView(asin, asinLayout);

        TextView open = button("Open Amazon", SURFACE_HIGH, PRIMARY);
        open.setOnClickListener(v -> open(item.buyUrl));
        LinearLayout.LayoutParams openLayout = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        openLayout.topMargin = dp(16);
        card.addView(open, openLayout);
        return card;
    }

    private String price(SearchResults.Item item) {
        if (item.amount.isEmpty()) return "Price unavailable";
        if ("USD".equals(item.currency)) return "$" + item.amount;
        return item.currency.isEmpty() ? item.amount : item.amount + " " + item.currency;
    }

    private void open(String url) {
        if (url == null || !(url.startsWith("https://") || url.startsWith("http://"))) {
            Toast.makeText(this, "No HTTP buy URL", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private LinearLayout column() {
        LinearLayout view = new LinearLayout(this);
        view.setOrientation(LinearLayout.VERTICAL);
        return view;
    }

    private TextView text(String value, float size, int color) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setIncludeFontPadding(false);
        return view;
    }

    private TextView button(String label, int background, int foreground) {
        TextView view = text(label, 14, foreground);
        view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        view.setGravity(Gravity.CENTER);
        view.setMinHeight(dp(48));
        view.setPadding(dp(18), dp(10), dp(18), dp(10));
        view.setBackground(box(background, 24, -1));
        view.setClickable(true);
        view.setFocusable(true);
        return view;
    }

    private GradientDrawable box(int color, int radius, int stroke) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(dp(radius));
        if (stroke >= 0) shape.setStroke(dp(1), stroke);
        return shape;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
