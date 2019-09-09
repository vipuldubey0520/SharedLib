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
import hudson.model.Fingerprint.RangeSet;  
def call() {

MAX_BUILDS = 4
def jobName = "N_D_P_P"
def job = Jenkins.instance.getItem(jobName)

println ""

println "selected Jenkins Job : "
println job.name

def recent = job.builds.limit(MAX_BUILDS)
println recent

  for (build in job.builds) {
    if (!recent.contains(build)) {
      println ""
      println "========================================================="
      println "Preparing to delete: " + build
      build.delete()
    println ""
    }
  }
}
