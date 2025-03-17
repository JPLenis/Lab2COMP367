pipeline {
    agent any

    environment {
        // This pulls the Docker Hub credentials stored in Jenkins.
        // Ensure the credential with ID 'dockerhub-credentials' has username "jplenis" (all lowercase).
        DOCKER_CREDS = credentials('dockerhub-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone your Maven project from GitHub 
                git branch: 'master', url: 'https://github.com/JPLenis/Lab2COMP367.git'
            }
        }
        stage('Build Maven Project') {
            steps {
                // Use 'bat' for Windows to run Maven commands
                bat 'mvn clean package'
            }
        }
        stage('Docker Login') {
            steps {
                script {
                    // Using the default Docker registry by passing an empty string
                    docker.withRegistry('', 'dockerhub-credentials') {
                        echo "Logged in to Docker Hub successfully."
                    }
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile in the repo and tag it with the Jenkins build number
                    dockerImage = docker.build("jplenis/lab3:${env.BUILD_NUMBER}")
                }
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    // Push the Docker image to Docker Hub using the same credentials
                    docker.withRegistry('', 'dockerhub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo "Pipeline execution completed."
        }
    }
}
