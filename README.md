Micronaut Protobuf GraalVM example
-------

This repository contains an example Micronaut-based project that works with Protobuf and 
successfully compiles into a GraalVM Native Image.

# How to build a native image

First, install and configure a GraalVM itself and install the `native-image` plugin.

Here is an example of how it could be done:

```bash
GRAALVM_VERSION="20.2.0"
JAVA_VERSION="11"
GRAALVM="graalvm-ce-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}"
GRAALVM_ARCHIVE="${GRAALVM}.tar.gz"
GRAALVM_JAVA="graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION}"

wget "https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/${GRAALVM_ARCHIVE}"

tar -xvzf "${GRAALVM_ARCHIVE}"

sudo mkdir /usr/lib/jvm
sudo mv "${GRAALVM_JAVA}" /usr/lib/jvm
rm "${GRAALVM_ARCHIVE}"


JAVA_SDK_PATH="/usr/lib/jvm/${GRAALVM_JAVA}"
sudo bash -c "echo \"export JAVA_HOME=${JAVA_SDK_PATH}\" >> /etc/profile.d/graalvm.sh"
sudo bash -c "echo \"export PATH=${JAVA_SDK_PATH}/bin:\\\$PATH\" >> /etc/profile.d/graalvm.sh"

source /etc/profile.d/graalvm.sh

## Install Native Image using GVM `gu` utility

gu install native-image
```

With GraalVM installed and configured, run the Native Image compilation using 
the following Gradle command:

```bash
./gradlew clean build nativeImage
```

# Running locally

When the image is built, you can simply run it as any other binary. The `application` executable
should be available under the `build/native-image` folder.
Also, a runnable fat JAR file should be available under the `build/libs` folder.

You can find the pre-built binaries in the [`bin`](./bin) folder.
