#!/bin/bash

# ==============================================================================
# Parking Management System - AWS EC2 Deployment Script
# Description: Automates the setup of Java, Apache Tomcat, and Environment 
# Variables on an Ubuntu EC2 instance to serve the web application.
# ==============================================================================

echo "Starting EC2 Server Setup for Parking Management System..."

# 1. Update the server's package index
echo "Updating system packages..."
sudo apt-get update -y
sudo apt-get upgrade -y

# 2. Install Java Development Kit (JDK 11)
# Required to run the compiled Java Servlets and JSP files
echo "Installing OpenJDK 11..."
sudo apt-get install openjdk-11-jdk -y

# Verify Java installation
java -version

# 3. Install Apache Tomcat 9
# Acts as the web server and servlet container for the application
echo "Installing Apache Tomcat 9..."
sudo apt-get install tomcat9 tomcat9-admin -y

# 4. Configure Secure Environment Variables for AWS RDS
# This creates a setenv.sh file that Tomcat runs on startup to securely load 
# the database credentials, ensuring they are never hardcoded in the .war file.
echo "Configuring environment variables..."

# NOTE: Replace the placeholders below with your actual AWS RDS details 
# before running this script on your EC2 instance!
sudo bash -c 'cat <<EOF > /usr/share/tomcat9/bin/setenv.sh
export RDS_DB_URL="jdbc:mysql://<your-rds-endpoint>:3306/adlab"
export RDS_DB_USER="admin"
export RDS_DB_PASS="your_secure_password"
EOF'

# Make the environment variable script executable
sudo chmod +x /usr/share/tomcat9/bin/setenv.sh

# 5. Restart Tomcat to apply the environment variables
echo "Restarting Tomcat server..."
sudo systemctl restart tomcat9

# 6. Enable Tomcat to automatically start if the EC2 instance is rebooted
sudo systemctl enable tomcat9

echo "=============================================================================="
echo "Setup Complete!"
echo "Next Step: Upload your ParkingSpaceManager.war file to /var/lib/tomcat9/webapps/"
echo "=============================================================================="