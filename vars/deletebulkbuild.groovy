/*** BEGIN META {
  "name" : "Bulk Delete Builds",
  "comment" : "For a given job and a given range of possible build numbers, delete those builds.",
  "parameters" : [ 'jobName', 'buildRange' ],
  "authors" : [
    { name : "Vipul" }
  ]
} END META**/


// NOTE: uncomment parameters below if not using Scriptler >= 2.0, or if you're just pasting
// the script in manually.

// The name of the job.
def call() {
//def jobName = "N_D_P"

// The range of build numbers to delete.
//def buildRange = "3-6"

//import jenkins.model.*;
//import hudson.model.Fingerprint.RangeSet;
//def j = jenkins.model.Jenkins.instance.getItem(jobName);

//def r = RangeSet.fromString(buildRange, true);

//j.getBuilds(r).each { it.delete() }

  //******
  
  //Jenkins.instance.getItemByFullName('N_D_P').builds.findAll { it.number > 2 && it.number < 6 }.each { it.delete() }
  
  //****
 // def jobName = "N_D_P"
//def job = Jenkins.instance.getItem(jobName)
//job.getBuilds().each { it.delete() }
//job.nextBuildNumber = 1
//job.save()
  
  //***
  
import jenkins.model.Jenkins
import hudson.model.Job

MAX_BUILDS = 4

for (job in Jenkins.instance.items) {
  println job.name

  def recent = job.builds.limit(MAX_BUILDS)

  for (build in job.builds) {
    if (!recent.contains(build)) {
      println "Preparing to delete: " + build
      build.delete()
    }
  }
}
}
