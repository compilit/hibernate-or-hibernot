#!/usr/bin/env bash

# Runs the regression test once per domain module profile, so the resulting JFR
# recordings can be compared side by side. Since `clean` wipes main/target/ (which is
# also where the recording lands), each .jfr is copied out to reports/ before moving
# on to the next profile.

set -euo pipefail

PROFILES=("spring-boot-data-jpa" "spring-data-jdbc")

usage() {
  echo "Usage: $0 <JAVA_HOME>"
  exit 1
}

[ $# -eq 1 ] || usage

JAVA_HOME="$1"
if [[ ! -x "$JAVA_HOME/bin/java" ]]; then
  echo "Error: no java executable found at ${JAVA_HOME}/bin/java" >&2
  exit 1
fi
export JAVA_HOME

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

export PATH="$JAVA_HOME/bin:$PATH"

REPORTS_DIR="$SCRIPT_DIR/reports"
mkdir -p "$REPORTS_DIR"

FAILED_PROFILES=()

for PROFILE in "${PROFILES[@]}"; do
  echo
  echo "=== Running regression test with profile: $PROFILE ==="

  # -pl (--projects) + -am (--also-make): only build main, plus whatever it depends on
  # (application, infrastructure, and-per the active profile-the matching domain module),
  # compiled from source rather than resolved from a possibly stale local repo.
  if ./mvnw -pl main -am clean test "-P$PROFILE"; then
    STATUS="passed"
  else
    STATUS="failed"
    FAILED_PROFILES+=("$PROFILE")
  fi

  JFR_FILE="main/target/regression-$PROFILE.jfr"
  if [[ -f "$JFR_FILE" ]]; then
    cp "$JFR_FILE" "$REPORTS_DIR/regression-$PROFILE.jfr"
    echo "Saved recording to reports/regression-$PROFILE.jfr (test run $STATUS)"
  else
    echo "Warning: no recording found at $JFR_FILE (test run $STATUS)" >&2
  fi
done

echo
echo "=== Summary ==="
for PROFILE in "${PROFILES[@]}"; do
  if [[ -f "$REPORTS_DIR/regression-$PROFILE.jfr" ]]; then
    echo "$PROFILE: reports/regression-$PROFILE.jfr"
  else
    echo "$PROFILE: no recording produced"
  fi
done

if [[ ${#FAILED_PROFILES[@]} -gt 0 ]]; then
  echo
  echo "Test failures occurred for: ${FAILED_PROFILES[*]}" >&2
  exit 1
fi