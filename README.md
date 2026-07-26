# Pitest Example

Multi-module Maven project including the **Maven** wrapper, configured for **Java 21**, with JUnit and PiTest.

[PIT](https://pitest.org/) is a state of the art mutation testing system.

## Modules

**root**: Root **pom.xml**: lists the modules, configures **SonarQube** and **JaCoCo**.

**pitest-example-dependencies**: **BOM** (Bill of Materials) centralizing dependency and plugin versions.

**pitest-example-parent**: Parent configuring the **Java** version and encoding, inheriting from **pitest-example-dependencies**.

**pitest-example-internal-parent**: Utility parent inheriting from **pitest-example-parent**, configuring **flatten-maven-plugin** in **OSSRH** mode.

**pitest-example-module-A** and **pitest-example-module-B**: Application modules, both inheriting from **pitest-example-internal-parent**.

**pitest-example-tests**: Module for aggregating test, and coverage results and pitest reports.

## Useful Commands (Maven)

- `./mvnw clean` : removes generated files
- `./mvnw compile` : compiles the project
- `./mvnw test` : runs unit tests
- `./mvnw verify` : runs tests and generates a pit report in **pitest-example-tests/target/pit-reports/**

## Adding a Module

To add a new module to the project:

1. Add the module in the root **pom.xml**.
2. Declare its dependency in the **dependencyManagement** block of the **pitest-example-dependencies** **pom.xml**.
3. Create the module with **pitest-example-internal-parent** as its parent.
4. Reference the module in the **pitest-example-tests** **pom.xml** so its coverage is aggregated.

## How to configure PiTest

Add the following block to the module's pom.xml:

```
	<build>
		<plugins>
			<plugin>
				<groupId>org.pitest</groupId>
				<artifactId>pitest-maven</artifactId>
				<executions>
					<execution>
						<id>run-mutation-tests</id>
						<goals>
							<goal>mutationCoverage</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<targetClasses>
						<param>fr.damnardev.example.moduleB.*</param>
					</targetClasses>
					<targetTests>
						<param>fr.damnardev.example.moduleB.*</param>
					</targetTests>
					<mutators>
						<mutator>ALL</mutator>
					</mutators>
					<exportLineCoverage>true</exportLineCoverage>
					<outputFormats>
						<value>XML</value>
					</outputFormats>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

1. Adapt the **targetClasses** and **targetTests** to your project.
2. Based on your needs, you can configure the mutators. See https://pitest.org/quickstart/mutators/ for more details.
