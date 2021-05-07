# Introspection targets
# ---------------------

.PHONY: help
help: targets

.PHONY: targets
targets:
	@echo "\033[34mTargets\033[0m"
	@echo "\033[34m---------------------------------------------------------------\033[0m"
	@perl -nle'print $& if m{^[a-zA-Z_-]+:.*?## .*$$}' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-22s\033[0m %s\n", $$1, $$2}'

# Build targets
# -------------

.PHONY: run-web-app
run-web-app: ## Run the web app
	./gradlew :web:browserDevelopmentRun --continuous

.PHONY: build-web-app-prod
build-web-demo-prod: ## Build the web demo
	./gradlew :web:browserProductionWebpack

# Check, lint and format targets
# ------------------------------

.PHONY: format
format: ## Format KMP
	./gradlew ktlintFormat

.PHONY: check-format
check-format: ## Check KMP format
	./gradlew ktlintCheck
