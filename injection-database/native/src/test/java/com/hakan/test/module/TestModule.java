package com.hakan.test.module;

import com.hakan.basicdi.annotations.Provide;
import com.hakan.spinjection.database.connection.credential.DbCredential;
import com.hakan.spinjection.module.PluginModule;

public class TestModule extends PluginModule {

	@Provide
	public DbCredential credential() {
		return DbCredential.of(
			"jdbc:mysql://localhost:3306/hakan?createDatabaseIfNotExist=true",
			"com.mysql.jdbc.Driver",
			"root",
			"admin"
		);
	}
}
