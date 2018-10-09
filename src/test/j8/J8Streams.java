package test.j8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class J8Streams {
	public static void main(String[] args)  throws IOException{
		System.out.println
        (IntStream
		.range(1, 10)
		.skip(5)
		.sum()
		);
		
		Stream.of("A","B","C")
		.sorted()
		.findFirst()
		.ifPresent(x -> {
			System.out.println(x);
		});
		
		String[] names = {"Ana","Pablo","Rodrigo","Yhon","Cristian","Raul"};
		Arrays.stream(names).
				filter(
						x -> x.startsWith("R")
				).sorted()
				.forEach(System.out::println);
		
		Arrays.stream(new int [] {2,4,6,8,10})
		.map(x -> x*x)
		.average()
		.ifPresent(System.out::println);
		
		
		List<String> people = Arrays.asList("Bod", "Alen","Cred","Fallen","Niki");
		people
		.stream()
		.map(String::toLowerCase)
		.filter(x -> x.startsWith("a"))
		.forEach(System.out::println);
		
		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
		bands
		.sorted()
		.filter(x -> x.length()> 5)
		.forEach(System.out::println);
		bands.close();
		
		List<String> bands2 =  Files.lines(Paths.get("bands.txt"))
		.filter(x-> x.contains("t"))
		.collect(Collectors.toList());
		bands2.forEach(z -> System.out.println(z));
		
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int) rows1
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.count();
		System.out.println("Rows: "+rowCount);
		rows1.close();
		
		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
		rows2
		.map(x -> x.split(","))
		.filter(x -> x.length == 3)
		.filter(x -> Integer.parseInt(x[1]) > 15)
		.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
		rows2.close();
		
		System.out.println("Example with Map");
		System.out.println("------------------------------------");
		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = rows3
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1])> 15)
				.collect(Collectors.toMap(
						x -> x[0],
						x -> Integer.parseInt(x[1])));
		rows3.close();
		map.forEach((x,y) -> System.out.println("key: "+ x + " Value: "+ y));
//		for(String key: map.keySet()) {
//			System.out.println(key +" "+ map.get(key));
//		}
		System.out.println("------------------------------------");
	}

	
}
