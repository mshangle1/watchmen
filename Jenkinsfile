library 'pipeline-utilities'
pipeline {
  agent {
    label 'jenkins-agent-maven'
  }

   environment {
          OC_PROJECT="devops-pipeline"
          SQ_SCANNER_HOME='/opt/sonar-scanner'
          CHECKMARX_NAME='104392-watchmen-2.3.0'
      }

   parameters {
           booleanParam(name: 'SONAR_SCAN', defaultValue: true, description: 'Sonar Scan (true/false)')
           booleanParam(name: 'CHECKMARX_SCAN', defaultValue: true, description: 'Checkmarx Scan (true/false)')

       }

      stages {
          stage("clone code") {
              steps {
                  script {
                      // Let's clone the source
                      checkout scm
                  }
              }
          }

       stage('Scan') {
                   parallel {
                       stage ('Checkmarx Scan') {
                           when {
                               expression { params.CHECKMARX_SCAN }
                           }
                           steps {
                               checkmarxScan projectName: env.CHECKMARX_NAME,
                                             incremental: false,
                                             teamPath: "CxServer\\SP\\Test\\EO\\DevOps Pipeline",
                                             highThreshold: 0,
                                             mediumThreshold: 3,
                                             lowThreshold: 5
                           }
                       }
                       stage('Sonar Scan') {
                           when {
                               expression { params.SONAR_SCAN }
                           }
                           steps {
                                   sonarScanGeneric abortPipeline: false,
                                   enableBranchAnalysis: true,
                                   args: ["-Dsonar.inclusions=src/main/java/com/*/**/* -Dsonar.projectKey=org.ally.spring-boot-canary -Dsonar.sources=. -Dsonar.java.binaries=target/*"]

                                 }
                       }
                   }
               }

          stage("mvn build and publish to nexus") {
              steps {
                  script {
                      // If you are using Windows then you should use "bat" step
                      // Since unit testing is out of the scope we skip them
                      // Maven will build artifact and deploy it to Nexus
                      // artifactId = cucumber-rest-assured
                      sh "mvn clean deploy -DskipTests=true"
                  }
              }
          }

      }
  }

