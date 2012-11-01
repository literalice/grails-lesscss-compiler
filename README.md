# LESS CSS compiler plugin for Grails #

This is the grails plugin to compile LESS CSS files.

The plugin adds a command to compile LESS files and compiles LESS files after a compile phase.

## Install ##

    //Build.groovy

    grails.project.dependency.resolution = {
        // ...
        repositories {
            // For the plugin
            mavenRepo "http://repository-monochromeroad.forge.cloudbees.com/release"
        }

        // ...
        plugins {
            build ":lesscss-compiler:1.0"
        }
    }

## Usage ##

1. Put LESS files(\*.less) to the **"src/less"** directory.

2. After a compile phase, the LESS files will be compiled and put to the **"web-app/css"** directory. The file name will be changed to **\*_less.css**

3. You can use the **"web-app/css/\*_less.css"** files in \*Resources.groovy or somewhere.

4. After you modify **"src/less/\*.less"** files, you should re/run the **less-compile** command.
