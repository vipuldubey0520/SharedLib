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
  
  // Jenkins job
def jobName = 'foo'
// Range of builds to delete
def rs = Fingerprint.RangeSet.fromString("2-5", false);
// Set to true to actually delete. Use false to test the script.
def reallyDelete = false;

// ----------------------------------
def job = Jenkins.instance.getItemByFullName(jobName);
println("Job: ${job.fullName}");

def builds = Jenkins.instance.getItemByFullName(jobName).getBuilds(rs);
println("Found ${builds.size()} builds");
builds.each{ b-> 
  if (reallyDelete) {
    println("Deleting ${b}");
    b.delete();
  } else {
    println("Found match ${b}");
  }
}
}
