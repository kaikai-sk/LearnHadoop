package hadoop.mapreduce;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "192.168.1.23:9001");
		Job job = new Job(conf);

		// 组建Job类，提交给mapreduce
		job.setJarByClass(hadoop.mapreduce.WordCountRunner.class);
		job.setMapperClass(hadoop.mapreduce.WcMapper.class);
		job.setReducerClass(hadoop.mapreduce.WcReducer.class);

		// 设置输入输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		// 构建输入输出的文件
		FileInputFormat.setInputPaths(job, "hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/input/wc/test");
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/output/wc/myres.txt"));

		// 提交job到mapreduce处理
		job.waitForCompletion(true);
	}
}