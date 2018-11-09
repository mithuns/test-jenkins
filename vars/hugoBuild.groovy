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

      stage('Download And Install hugo if not present') {
        steps{
          checkout scm
        }
      }
    }
  }
  }finally{
  }
}
