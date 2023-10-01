cd ..
mvn clean package -DskipTests
mkdir target/dependency
(cd target/dependency; jar -xf ../*.jar)