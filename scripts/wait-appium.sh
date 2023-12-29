#!/bin/bash

set -e

timeout=10
while [ $timeout -gt 0 ]; do
    if nc -z localhost 4723; then
        echo "Appium server is up and running."
        break
    fi
    echo "Waiting for Appium server to start..."
    sleep 1
    ((timeout--))
done

if [ $timeout -eq 0 ]; then
    echo "Timeout: Appium server did not start within 10 seconds."
    exit 1
fi
