package hadoop.mapreduce.qqFriend;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class QQFriendRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "192.168.1.23:9001");
		conf.set("mapred.jar", "C:\\Users\\shankai\\Desktop\\qqFriendRecommend.jar");
		Job job = new Job(conf);

		// 组建Job类，提交给mapreduce
		job.setJobName("QQ Friend recommend");
		job.setJarByClass(hadoop.mapreduce.qqFriend.QQFriendRunner.class);
		job.setMapperClass(hadoop.mapreduce.qqFriend.QQFriendMapper.class);
		job.setReducerClass(hadoop.mapreduce.qqFriend.QQFriendReducer.class);

		// 设置输入输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(LongWritable.class);

		// 构建输入输出的文件
		FileInputFormat.setInputPaths(job, "hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/input/qq/test");
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/output/qq/"));

		// 提交job到mapreduce处理
		job.waitForCompletion(true);
	}
}