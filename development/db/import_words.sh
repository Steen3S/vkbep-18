#!/bin/bash
set -e

echo "Importing words (source: OpenTaal)"

PGPASSWORD=admin psql --username admin ${DB_APP_NAME} --file /data/lingo_words.sql