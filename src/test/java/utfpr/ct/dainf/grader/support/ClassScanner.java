package utfpr.ct.dainf.grader.support;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.testng.Reporter;

/**
 * Scans the classpath and collects information about classes.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class ClassScanner {
    private final List<ClassEntry> classes = new ArrayList<>();
    private final String rootPackage;
    private final URL rootURL;
    private final String rootPath;

    public ClassScanner() {
        this("");
    }

    public ClassScanner(String rootPackage) {
        this.rootPackage = rootPackage;
        rootURL = getRootURL();
        rootPath = rootURL.getPath();
        scan();
    }
    
    public boolean contains(ClassEntry ce) {
        return classes.contains(ce);
    }
    
    public boolean contains(String qcn) {
        return classes.contains(new ClassEntry(qcn));
    }
    
    public boolean contains(Class c) {
        return contains(c.getName());
    }
    
    public ClassEntry findEntry(ClassEntry ce) {
        int i = classes.indexOf(ce);
        return i < 0 ? null : classes.get(i);
    }
    
    public ClassEntry findEntry(String qcn) {
        int i = classes.indexOf(new ClassEntry(qcn));
        return i < 0 ? null : classes.get(i);
    }
    
    public List<ClassEntry> findClassesByName(String name) {
        List<ClassEntry> ces = new ArrayList<>();
        for (ClassEntry ce: classes) {
            if (ce.isSameClass(name)) {
                ces.add(ce);
            }
        }
        return ces;
    }
    
    public ClassEntry findClassByName(String name) {
        for (ClassEntry ce: classes) {
            if (ce.isSameClass(name)) {
                return ce;
            }
        }
        return null;
    }
    
    private ClassEntry extractClassEntryFromPath(String path) {
        String pkg = path.substring(rootPath.length()-1);
        pkg = pkg.substring(0, pkg.lastIndexOf("."));
        return new ClassEntry(pkg.replace(File.separator, "."));
    }
    
    private ClassEntry extractClassEntryFromPath(File path) {
        return ClassScanner.this.extractClassEntryFromPath(path.getPath());
    }
    
    private URL getRootURL() {
        URL url = null;
        try {
            Enumeration<URL> urls = ClassLoader.getSystemResources(rootPackage);
            while (urls.hasMoreElements()) {
                url = urls.nextElement();
                if (!url.toString().endsWith("/test-classes/")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
    
    /**
     * Recursively scans the classpath starting from root.
     * Stores qualified class names into a ArrayList.
     * @param root The root of the classpath to scan.
     */
    private void scan(File root) {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || (pathname.isFile()
                        && pathname.getName().endsWith(".class"));                
            }
        });
        
        for (File f: files) {
            if (f.isDirectory())
                scan(f);
            else {
                classes.add(extractClassEntryFromPath(f));
            }
        }
    }
    
    private void scan() {
        try {
            scan(new File(getRootURL().toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
}
