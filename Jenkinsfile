pipeline {

    agent any

    environment {
        HARBOR_URL = 'http://...'
        //requires "Credentials Binding" Plugin
        SERVER_CREDENTIALS = credentials('gitlab-credentials')
    }

    parameters {
        booleanParam(name: 'executeTests', defaultValue: true, description: 'execute unit tests when true')
    }

    //tools {
        //smaven 'Maven3'
        //jdk 'JDK'
    //}

    triggers {
        pollSCM "*/10 * * * *"
    }

    stages {
        stage("build") {
            steps{
                echo '### building artifact ###'
                sh 'mvn -B -DskipTests clean package'
            }
            post {
                always {
                    echo '### maven build completed  ###'
                }
                success {
                    echo '### maven build successful  ###'
                }
                failure {
                    echo '### maven build failed  ###'
                }
            }
        }

        stage("test") {
            when{
                //params.executeTests true
                expression{
                    BRANCH_NAME ==~ /(dev|main)/
                }
                    //expression{ env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'main' }
            }
            steps{
                echo '### testing artifact ###'
                sh 'mvn test'
            }
            post {
                always {
                    echo '### unit test completed  ###'
                    junit 'target/surefire-reports/*.xml'
                }
                success{
                    echo '### unit test successful  ###'
                }
                failure{
                    echo '### unit test failed  ###'
                }
            }
        }

        stage("build image") {
            when {
                branch 'master'
            }
            steps{
                echo '### TODO building container image ###'
            }
            post{
                always{
                    echo '### image build completed  ###'
                }
                success{
                    echo '### image build successful  ###'
                }
                failure{
                    echo '### image build failed  ###'
                }
            }
        }

        stage("push image"){
            when {
                branch 'master'
            }
            steps{
                echo '### TODO: pushing image to ${HARBOR_URL} ###'
            }
            post{
                always{
                    echo '### image push completed  ###'
                }
                success{
                    echo '### image push successful  ###'
                }
                failure{
                    echo '### image push failed  ###'
                }
            }
        }

        stage("local image deletion"){
            steps{
                echo '### TODO: deleting local image  ###'
            }
            post{
                always{
                    echo '### local image deletion completed  ###'
                }
                success{
                    echo '### local image deletion successful  ###'
                }
                failure{
                    echo '### local image deletion failed  ###'
                }
            }
        }
    }

    post{
        always{
            echo '### pipeline execution completed   ###'
        }
        success{
            echo '### pipeline execution successful   ###'
        }
        failure{
            echo '### pipeline execution failed   ###'
        }
    }
}