includeTargets << new File("${lesscssCompilerPluginDir}/scripts/_LessCompile.groovy")

eventCompileEnd = {
    compileLessCss()
}

