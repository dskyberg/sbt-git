sbtPlugin := true

name := "sbt-git"
organization := "com.typesafe.sbt"


git.baseVersion := "0.8.6"


libraryDependencies ++= Seq(
  "org.eclipse.jgit" % "org.eclipse.jgit.pgm" % "4.5.0.201609210915-r"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
)
publishMavenStyle := false


scriptedSettings
scriptedLaunchOpts += s"-Dproject.version=${version.value}"

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://nexus.dev.confyrm.com/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "content/repositories/releases")
}

pomIncludeRepository := { x => false }

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pomExtra := (
  <scm>
    <url>git@github.com:confyrm/auth-service.git</url>
    <connection>scm:git:git@github.com:confyrm/auth-service.git</connection>
  </scm>
  <developers>
    <developer>
      <id>david</id>
      <name>David Skyberg</name>
    </developer>
  </developers>)