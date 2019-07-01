# Spring Boot systemd notification

> SystemdNotificationService that notifies systemd after your application context is ready.

[<img src="https://opensourcelogos.aws.dmtech.cloud/dmTECH_opensource_logo.svg" height="20" width="130">](https://dmtech.de/)
[![Build Status](https://travis-ci.org/dm-drogeriemarkt/spring-boot-systemd-notification.svg?branch=master)](https://travis-ci.org/dm-drogeriemarkt/spring-boot-systemd-notification)

## Usage

Declare dependency in a Spring Boot service:

    <dependency>
        <groupId>de.dm.infrastructure</groupId>
        <artifactId>spring-boot-systemd-notification</artifactId>
        <version>1.0.3</version>
    </dependency>

If Autoconfiguration is enabled and property systemd.notification.enabled is set to true this
autoconfigures a SystemdNotificationService and notifies systemd after your application context is ready.

Properties:

    systemd.notification.enabled=true

Sample systemd config:

    [Unit]
    Description="Servicename"
    After=network.target

    [Service]
    ExecStart=/usr/bin/java -server -Djna.tmpdir=/var/lib/servicename -jar /srv/servicename.jar
    User=someuser
    Group=somegroup
    Type=notify

    [Install]
    WantedBy=multi-user.target

## Prerequisits

* Service is deployed via systemd
* service can execute in a temp directory via JNA, can be set via -Djna.tmpdir= if you don't
  want to use the default temp directory
* Spring Boot autoconfiguration or bean declaration for SystemdNotificationService

## License

Copyright (c) 2018 dmTECH GmbH, https://www.dmtech.de

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
