PREFIX ?= /usr/local
DESTDIR ?=
SHELL ?= /bin/sh

.PHONY: test test-sms test-qwen-live test-qwen-live-chat test-qwen-live-responses install

test:
	bash test/az-test.sh
	bash test/abe-test.sh
	bash test/aa-test.sh
	bash test/zillow-test.sh
	bash test/qwen-alibaba-test.sh
	sh -n bin/idric_sms_service
	sh -n test/sms-service-test.sh

test-sms:
	sh test/sms-service-test.sh

test-qwen-live: test-qwen-live-chat test-qwen-live-responses

test-qwen-live-chat:
	ysh scripts/qwen-alibaba-chat-live.ysh

test-qwen-live-responses:
	ysh scripts/qwen-alibaba-responses-live.ysh

install:
	install -d "$(DESTDIR)$(PREFIX)/bin"
	install -m 0755 bin/az "$(DESTDIR)$(PREFIX)/bin/az"
	install -m 0755 bin/abe "$(DESTDIR)$(PREFIX)/bin/abe"
	install -m 0755 bin/aa "$(DESTDIR)$(PREFIX)/bin/aa"
	install -m 0755 bin/zillow "$(DESTDIR)$(PREFIX)/bin/zillow"
	install -m 0755 bin/qwen_alibaba "$(DESTDIR)$(PREFIX)/bin/qwen_alibaba"
	install -m 0755 bin/idric_sms_service "$(DESTDIR)$(PREFIX)/bin/idric_sms_service"
