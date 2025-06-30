#!/bin/sh

# Extract instance ID from the hostname
INSTANCE_ID=$(hostname | grep -o '[0-9]*$')

# Export the instance-specific environment variable
export INSTANCE_ID=${INSTANCE_ID:-0}

# Start the application
exec java -XX:UseSVE=0 -jar app.jar