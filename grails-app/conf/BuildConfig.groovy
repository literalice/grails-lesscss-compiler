grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.release.scm.enabled = false

private repositoryConfigLoader(name, path) {
    def centralCredentialLocation = System.getProperty(path)
    if (centralCredentialLocation) {
        def credentialFile = new File(centralCredentialLocation)
        if (credentialFile.canRead()) {
            def credential = new Properties()
            //noinspection GroovyMissingReturnStatement
            credentialFile.withReader {
                credential.load(it)
            }

            for (param in ["url", "username", "password", "type", "portal"]) {
                if (credential.get(param)) {
                    grails.project.repos."${name}"."${param}" = credential.get(param)
                }
            }
            println "Repository Configuration $name correctly loaded."
        } else {
            throw new IllegalStateException("Grails Central Credential File couldn't be read.")
        }
    }
}

for (repositoryName in ["releaseRepository", "snapshotRepository"]) {
    repositoryConfigLoader(repositoryName, "${repositoryName}.credential.properties")
    for (param in ["url", "type", "portal"]) {
        if (grails.project.repos."${repositoryName}"."${param}") {
            print "$repositoryName / $param : "
            print "$repositoryName / $param : "
            println grails.project.repos."${repositoryName}"."${param}"
        }
    }
}

grails.project.dependency.resolution = {
    inherits("global") {
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenCentral()
    }
    dependencies {
        build("org.lesscss:lesscss:1.3.1")
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.0.4",
              ":rest-client-builder:1.0.2") {
            export = false
        }
    }
}
