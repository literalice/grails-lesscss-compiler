import groovy.io.FileType
import org.lesscss.LessCompiler
import org.lesscss.LessException

target(compileLess: "Compiles all of the less css files.") {
    compileLessCss()
}

//noinspection GroovyMissingReturnStatement
compileLessCss = {
    def baseDir = grailsSettings.baseDir as File
    def lessDir = new File(baseDir, "src/less")
    LessCssCompiler lessCssCompiler = new LessCssCompiler(baseDir)
    if (lessDir.canRead()) {
        lessDir.eachFileRecurse(FileType.FILES) {
            if (!it.isHidden() && it.name ==~ /.+\.less/)
                lessCssCompiler.compile(it)
        }
    }
}

/**
 * @author Masatoshi Hayashi
 */
class LessCssCompiler {

    private File baseDir
    private LessCompiler lessCompiler = new LessCompiler()

    LessCssCompiler(File baseDir) {
        this.baseDir = baseDir
    }

    File compile(File resource) {
        File target = new File(generateCompiledFileFromOriginal(resource.absolutePath))
        try {
            lessCompiler.compile resource, target
            return target
        } catch (LessException e) {
            System.err.println(e.message)
            return null
        }
    }

    private String generateCompiledFileFromOriginal(String original) {
        String orgDirPath = /${baseDir.absolutePath}${File.separator}src${File.separator}less${File.separator}/
        String targetDirPath = /${baseDir.absolutePath}${File.separator}web-app${File.separator}css${File.separator}/
        original.replace(orgDirPath, targetDirPath).replaceAll(/(?i)\.less/, '_less.css')
    }

}

