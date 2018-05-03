package zip;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;

public class MavenLocChecker {

	public static void main(String[] args) {
		if(args == null|| args.length != 1) {
			System.out.println("please input MAVEN_REPOSITORY_DIR as param.");
			System.exit(-1);
		}
		String dir = args[0];
		Collection<File> files = FileUtils.listFiles(new File(dir), new String[] { "jar" },
				true);
		System.out.println("reading "+ files.size()+" jar files.");
		for (File f : files) {
			ZipFile z = null;
			try {
				z = new ZipFile(f);
				z.size();
				z.close();
			} catch (IOException e) {
				System.err.println("DELETE : "+f.getName() +", cause : "+ e.getMessage());
				FileUtils.deleteQuietly(f);
			}
		}
		System.out.println("done........");
	}

}
