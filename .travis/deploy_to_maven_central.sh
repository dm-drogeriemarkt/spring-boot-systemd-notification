#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ] || [ ! -z "$TRAVIS_TAG" ]; then
    openssl aes-256-cbc -K $encrypted_b5d5f6d3ac93_key -iv $encrypted_b5d5f6d3ac93_iv -in .travis/codesigning.asc.enc -out .travis/codesigning.asc -d
    gpg --fast-import .travis/codesigning.asc

    if [ ! -z "$TRAVIS_TAG" ]
    then
        echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
        ./mvnw --settings .travis/mvnsettings.xml org.codehaus.mojo:versions-maven-plugin:2.1:set -DnewVersion=$TRAVIS_TAG 1>/dev/null 2>/dev/null
    else
        echo "not on a tag -> keep snapshot version in pom.xml"
    fi

    ./mvnw deploy -P sign,build-extras --settings .travis/mvnsettings.xml -DskipTests=true -B -U
fi
