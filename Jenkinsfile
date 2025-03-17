pipeline {
    agent any

    // Set up environment variables; DOCKER_CREDENTIALS will pull your Docker Hub credentials from Jenkins.
    environment {
        DOCKER_CREDENTIALS = credentials('docker-credential-id') // Replace with your actual credentials ID
    }

    stages {
        // Step 1: Checkout - Clones your Maven project repository.
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/your-maven-project.git'
            }
        }
        
        // Step 2: Build Maven Project - Runs Maven to clean and package your project.
        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        // Stage 3: Docker Login - Authenticates with Docker Hub.
        stage('Docker Login') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-credential-id') {
                        echo "Logged in to Docker Hub successfully."
                    }
                }
            }
        }
        
        // Step 4: 
        stage('Docker Build') {
            steps {
                script {
                    // The image is tagged with the current build number for versioning.
                    dockerImage = docker.build("your-dockerhub-username/your-image-name:${env.BUILD_NUMBER}")
                }
            }
        }
        
        // Step 5: Docker Push - Pushes the built Docker image to Docker Hub.
        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-credential-id') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
    
    // Post actions: These run regardless of the pipeline's success/failure.
    post {
        always {
            echo "Pipeline execution completed."
        }
    }
}
