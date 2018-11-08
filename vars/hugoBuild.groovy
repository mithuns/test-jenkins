def call(body) {
  def config = [:]
  def emptyList = []
  def branchName = BRANCH_NAME
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  if(!config.numberOfBuildsToKeep) {
        config.numberOfBuildsToKeep = 3
  }


  if(!config.notifyCulpritsOnBranchFailures){
    config.notifyCulpritsOnBranchFailures = true
  }

  try {
    pipeline {
      agent any

    /**
    * These are the stages of our pipeline. This compiles our code, builds the site, and deploys it.
    */
    stages {

      stage('Checkout') {
        steps{
        checkout scm
        }

      }

      stage('Install') {
        steps {
        }
      }

      stage('SonarQube Branch Analysis') {
        steps {
        }
      }

      stage('Master branch : SonarQube Analysis') {
        steps {
        }
      }

      stage('Publish Test Reports'){
        steps {
        }
      }

      stage ('Deploy Artifacts') {
        steps {
        }
      }

      stage('Publish Site') {
        steps{
        }
      }
    }
  }
  }finally{
    notify {
              culpritOnBranchFailures = config.notifyCulpritsOnBranchFailures
              emailRecipientsForMasterFailures = config.masterFailuresEmailTo
              teamsWebhook = config.teamsWebhook
    }
  }
}
