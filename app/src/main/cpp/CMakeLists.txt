# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

set(sfml_dir ${CMAKE_SOURCE_DIR}/../sfml)
set(sfml_lib ${sfml_dir}/lib/${ANDROID_ABI})
set(sfml_extlib ${sfml_dir}/extlibs/lib/${ANDROID_ABI})
set(sfml_include ${sfml_dir}/include)

# Declares and names the project.

project("sfmltest")

include(ndk-stl-config.cmake)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp )

add_library(sfml-main-d STATIC IMPORTED)
set_target_properties(sfml-main-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-main-d.a)

add_library(sfml-activity-d SHARED IMPORTED)
set_target_properties(sfml-activity-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-activity-d.so)

add_library(sfml-system-d SHARED IMPORTED)
set_target_properties(sfml-system-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-system-d.so)

add_library(sfml-window-d SHARED IMPORTED)
set_target_properties(sfml-window-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-window-d.so)

add_library(sfml-graphics-d SHARED IMPORTED)
set_target_properties(sfml-graphics-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-graphics-d.so)

add_library(sfml-audio-d SHARED IMPORTED)
set_target_properties(sfml-audio-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-audio-d.so)

add_library(sfml-network-d SHARED IMPORTED)
set_target_properties(sfml-network-d PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-network-d.so)

add_library(openal SHARED IMPORTED)
set_target_properties(openal PROPERTIES IMPORTED_LOCATION ${sfml_extlib}/libopenal.so)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

target_include_directories(native-lib PRIVATE ${sfml_include})

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                        android

                        EGL
                        GLESv1_CM

                        sfml-audio-d
                        openal
                        sfml-system-d
                        sfml-window-d
                        sfml-graphics-d
                        sfml-activity-d
                        sfml-network-d

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
                        -Wl,--whole-archive sfml-main-d -Wl,--no-whole-archive
                        )