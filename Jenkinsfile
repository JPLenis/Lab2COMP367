pipeline {
    agent any

    // This environment variable pulls the Docker Hub credentials (username and password or token)
    environment {
        DOCKER_CREDS = credentials('dockerhub-credentials')
    }

    stages {
        // Step 1: Checkout the Maven project from GitHub
        stage('Checkout') {
            steps {
                // Cloning from your GitHub repository using the credential ID 'lab2'
                git branch: 'main', credentialsId: 'lab2', url: 'https://github.com/JPLenis/Lab2COMP367.git'
            }
        }
        
        // Step 2: Build the Maven project
        stage('Build Maven Project') {
            steps {
                // Run Maven clean and package to build the project
                sh 'mvn clean package'
            }
        }
        
        // Stage 3: Docker Login using the Docker Hub credentials
        stage('Docker Login') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        echo "Logged in to Docker Hub successfully."
                    }
                }
            }
        }
        
        // Step 4: Build the Docker image using the Dockerfile from the repository
        stage('Docker Build') {
            steps {
                script {
                    // Build the Docker image and tag it using the Jenkins build number for versioning
                    dockerImage = docker.build("jplenis/lab2comp367:${env.BUILD_NUMBER}")
                }
            }
        }
        
        // Step 5: Push the Docker image to Docker Hub
        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
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
