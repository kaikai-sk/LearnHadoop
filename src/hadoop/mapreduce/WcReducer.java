package hadoop.mapreduce;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class WcReducer extends Reducer<Text, LongWritable, Text, LongWritable> {//±ØÐë¼Ì³ÐReducer

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values,
            Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable number : values) {
            count += number.get();
        }
        context.write(new Text(key), new LongWritable(count));
    }
}