#!/bin/bash

cd "$(dirname "$0")/.."

mkdir -p backups

if [ -f data/credentials.csv ]; then
    cp data/credentials.csv "backups/credentials_backup_$(date +%Y%m%d_%H%M%S).csv"
    echo "Backup completed successfully."
else
    echo "No credentials file found."
fi