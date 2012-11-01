includeTargets << new File("${grailsSettings.baseDir}/scripts/_LessCompile.groovy")

eventCompileEnd = {
    compileLessCss()
}

