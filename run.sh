#!/usr/bin/env bash

# Kickstarts the application using the specified profile for building the desired domain module.

set -euo pipefail

usage() {
  echo "Usage: $0 <spring-boot-data-jpa|spring-data-jdbc> <JAVA_HOME>"
  exit 1
}

[ $# -eq 2 ] || usage

PROFILE="$1"

JAVA_HOME="$2"
if [[ ! -x "$JAVA_HOME/bin/java" ]]; then
  echo "Error: no java executable found at ${JAVA_HOME}/bin/java" >&2
  exit 1
fi
export JAVA_HOME

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

export PATH="$JAVA_HOME/bin:$PATH"

# -pl (--projects) tells Maven to only operate on the listed module(s) instead of the whole
# multi-module reactor. Here we use main, our entry-point.

# -am (--also-make) tells Maven to also build whatever modules the selected project(s) depend
# on, in the correct order, first. Without it, Maven would try to build only main in isolation
# and fail to resolve presentation, application, infrastructure, etc. if they aren't already
# installed in your local repo.
./mvnw -pl main -am clean package -DskipTests "-P $PROFILE"
java -jar main/target/main-1.0-SNAPSHOT.jar