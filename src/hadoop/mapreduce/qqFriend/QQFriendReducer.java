package hadoop.mapreduce.qqFriend;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class QQFriendReducer extends Reducer<Text, Text, Text, Text>
{
	@Override
	protected void reduce(Text key, Iterable<Text> iterable,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException
	{
		Set<String> set=new HashSet<String>();
		for(Text t:iterable)
		{
			set.add(t.toString());
		}
		if(set.size()>1)
		{
			for(Iterator j=set.iterator();j.hasNext();)
			{
				String name=(String)j.next();
				for(Iterator<String> k=set.iterator();k.hasNext();)
				{
					String other=k.next();
					if(!name.equals(other))
					{
						context.write(new Text(name), new Text(other));
					}
				}
			}
		}
	}
}
