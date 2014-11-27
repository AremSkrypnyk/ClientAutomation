#!/bin/sh
cd ~/Documents/Tools/adt-bundle-mac/sdk/platform-tools
./adb logcat -d -s "FlurryController" | awk -F: '{print $3, $4}' | sed 's/^ *//' > ~/Documents/ClientAutomation/usage/deviceLogs/deviceFlurryLogs.txt