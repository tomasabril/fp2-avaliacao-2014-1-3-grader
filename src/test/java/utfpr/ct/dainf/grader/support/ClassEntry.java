package utfpr.ct.dainf.grader.support;

import java.util.Objects;

/**
 *
 * @author Wilson
 */
public class ClassEntry {
    private String pkg;
    private String name;

    public ClassEntry(String pkg, String name) {
        this.pkg = pkg;
        this.name = name;
    }
    
    public ClassEntry(String qcn) {
        setEntry(qcn);
    }

    public String getPackage() {
        return pkg;
    }

    public String getName() {
        return name;
    }
    
    private void setEntry(String qcn) {
        int pos = qcn.lastIndexOf(".");
        if (pos == -1) {
            pkg = null;
            name = qcn;
        } else {
            pkg = qcn.substring(0, pos);
            name = qcn.substring(pos+1);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.pkg);
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    public boolean isSamePackage(String pkg) {
        return (this.pkg == null && pkg == null) ||
               (this.pkg != null && this.pkg.equals(pkg)) ||
               (pkg != null && pkg.equals(this.pkg));
    }
    
    public boolean isSamePackage(ClassEntry ce) {
        return isSamePackage(ce.pkg);
    }
    
    public boolean isSameClass(String name) {
        return this.name.equals(name);
    }
    
    public boolean isSameClass(ClassEntry ce) {
        return isSameClass(ce.name);
    }
    
    @Override
    public boolean equals(Object other) {
        return getClass() == other.getClass() &&
            isSamePackage((ClassEntry)other) && isSameClass((ClassEntry)other);
    }
    
    public Class getClassObject() {
        try {
            return Class.forName(toString(), false, ClassLoader.getSystemClassLoader());
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s", pkg == null ? "" : pkg + '.', name);
    }
}
