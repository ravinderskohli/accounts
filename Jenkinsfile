		pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
 bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=accounts-sonarqube -Dsonar.projectName=accounts-sonarqub -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_e615dd9c8eafefa2132493534657bda4b375be8a'
            }
   
        }
	        
        
    }
}
