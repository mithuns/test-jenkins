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

  try 
  {
  	pipeline 
	  {
   		agent any
   		stages 
		{
		   stage('Source') 
		{
			steps{
				scm checkout
			}	 
		}
      		stage('Build') 
			{
         
      		}
   		}
  	}
  }finally{
  }
}
