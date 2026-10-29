# heads-of-governments-spring-data

Spring Data JPA repositories, `OfficeRepository` and `HeadRepository`, for the entities of
[heads-of-governments-persistence](../heads-of-governments-persistence), tested with `@SpringBootTest`.

## The database

`heads_of_governments.sqlite3`, which the heads-of-governments-persistence artifact carries at the root of its class
path, opened read-only. The tests open it from the class path
([application.yaml](src/test/resources/application.yaml)):

```
jdbc:sqlite::resource:heads_of_governments.sqlite3?open_mode=1&jdbc.explicit_readonly=true
```

`open_mode=1` opens the file read-only, and `jdbc.explicit_readonly=true` lets read-only transactions call
`Connection#setReadOnly`, which the driver refuses otherwise once the connection is open.

### As an IntelliJ data source

A relative path resolves against IntelliJ's own working directory, not the project's; use the `$PROJECT_DIR$` macro
instead. With this module opened as the project:

```
jdbc:sqlite:$PROJECT_DIR$/../heads-of-governments-persistence/src/main/resources/heads_of_governments.sqlite3?open_mode=1&jdbc.explicit_readonly=true
```

With the book repository opened as the project:

```
jdbc:sqlite:$PROJECT_DIR$/projects/heads-of-governments-persistence/src/main/resources/heads_of_governments.sqlite3?open_mode=1&jdbc.explicit_readonly=true
```

The **Read-only** checkbox on the data source's **Options** tab does the same on IntelliJ's side.

