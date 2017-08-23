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

		// �齨Job�࣬�ύ��mapreduce
		job.setJobName("QQ Friend recommend");
		job.setJarByClass(hadoop.mapreduce.qqFriend.QQFriendRunner.class);
		job.setMapperClass(hadoop.mapreduce.qqFriend.QQFriendMapper.class);
		job.setReducerClass(hadoop.mapreduce.qqFriend.QQFriendReducer.class);

		// ���������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(LongWritable.class);

		// ��������������ļ�
		FileInputFormat.setInputPaths(job, "hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/input/qq/test");
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.1.23:9000/opt/hadoop-1.2.1/mapred/output/qq/"));

		// �ύjob��mapreduce����
		job.waitForCompletion(true);
	}
}