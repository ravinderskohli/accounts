		pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
 bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=accounts-sonarqube -Dsonar.projectName=accounts-sonarqub -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_502105b65c95b8c58842a08d55007afdb22e30f4'
            }
   
        }
	        
        
    }
}
