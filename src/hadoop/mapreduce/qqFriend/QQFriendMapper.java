package hadoop.mapreduce.qqFriend;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class QQFriendMapper extends Mapper<LongWritable, Text, Text, Text>
{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException
	{
		String line=value.toString();
		String[] ss=line.split(" ");
		context.write(new Text(ss[0]), new Text(ss[1]));
		context.write(new Text(ss[1]), new Text(ss[0]));
	}
}
