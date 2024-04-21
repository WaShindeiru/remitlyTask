# Remitly Internship Home Exercise

## Test
To run tests execute:
```bash
./mvnw test
```

## Installation
To create an executable jar execute:
```bash
./mvnw clean package
```

After that the jar will be crated inside target directory

## Execution
To run the program you can execute the following command from the project directory
```bash
java -jar ./target/Remitly-1.0-SNAPSHOT-spring-boot.jar <JSON path>
```

This program accepts a filepath of the JSON to be validated


## Test description
All tests are contained within JSONParserTest class:
- `validateInvalid()` - simple json which has a single star in `Resources` field
- `validateValidMultipleResources()` - json has multiple `Resources` but none of them contains a single star
- `validateInvalidMultipleResources()` - json has multiple `Resources`, one of them has a single star
- `validateValidMultipleStatementsOneResource()` - json has an array of `Statement`, none resource consists of single star
- `validateInvalidMultipleStatementsOneResource()` - json has an array of `Statement`, one `Statement` has a `Resource` consisting of a single star
- `validateEmpty()` - json is empty
- `validateWrongSchemaRedundantLocationField()` - json has one redundat field - `Location`, `Resource` doesn't have a star
- `validateNoResource()` - json doesn't have a `Resource` field
