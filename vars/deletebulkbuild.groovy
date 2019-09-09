/*** 
  "name" : "Bulk Delete Builds",
  "comment" : "For a given job and a given range of possible build numbers, delete those builds.",
  "parameters" : [ 'jobName', 'buildRange' ],
  "authors" : [
    { name : "Vipul" }
  ]
} **/




import jenkins.model.*;
import hudson.model.Fingerprint.RangeSet;  
def call() {
  // The name of the job.
def jobName = "N_D_P_P"

// The range of build numbers to delete.
def buildRange = "21-47"
def j = jenkins.model.Jenkins.instance.getItem(jobName);
def r = RangeSet.fromString(buildRange, true);
j.getBuilds(r).each { it.delete() }

}
