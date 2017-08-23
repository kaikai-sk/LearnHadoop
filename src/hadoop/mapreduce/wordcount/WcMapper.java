package hadoop.mapreduce.wordcount;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WcMapper extends Mapper<LongWritable, Text, Text, LongWritable>
{// ����̳�Mapper
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String line = value.toString();// �ó�һ��
		String[] words = line.split(" ");
		for (String word : words)
		{
			context.write(new Text(word), new LongWritable(1));
		}
	}
}