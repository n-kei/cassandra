package test;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;

public class CassandraTest {
	private static StringSerializer stringSerializer = StringSerializer.get();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// create cluster
		// create key space
		Cluster cluster = HFactory.getOrCreateCluster("Test Cluster", "192.168.1.110:9042");
		Keyspace keyspace = HFactory.createKeyspace("keyspace2", cluster);
		
		// add data
		Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
		mutator.addInsertion("hoge", "first_table", HFactory.createStringColumn("value", "fuga"));
		mutator.addInsertion("piyo", "first_table", HFactory.createStringColumn("value", "hage"));
		mutator.execute();
		
		// get data
		ColumnQuery<String, String, String> columnQuery = HFactory.createColumnQuery(keyspace, stringSerializer, stringSerializer,
				stringSerializer);
		columnQuery.setColumnFamily("first_table").setKey("piyo").setName("value");
		QueryResult<HColumn<String, String>> result = columnQuery.execute();
		System.out.println(result);
		
		// remove data
	//	Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
	//	mutator.delete("hoge", "first_table", "value", stringSerializer);
	//	mutator.execute();
		
		// update data
	//	Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
	//	mutator.delete("hoge", "first_table", "value", stringSerializer);
	//	mutator.execute();
	}
}
