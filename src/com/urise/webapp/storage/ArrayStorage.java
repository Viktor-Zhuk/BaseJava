package com.urise.webapp.storage;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int i = searchResume(resume.getUuid());
        if (i != -1) {
            storage[i] = resume;
            System.out.println("Resume " + resume.getUuid() + " successfully updated");
        } else {
            System.out.println("ERROR: resume " + resume.getUuid() + " not found");
        }
    }

    public void save(Resume resume) {
        int i = searchResume(resume.getUuid());
        if (i != -1) {
            System.out.println("ERROR: resume " + resume.getUuid() + " already recorded");
        } else if (i == storage.length){
            System.out.println("ERROR: the resume database is filled in");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = searchResume(uuid);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("Resume " + uuid + " not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int i = searchResume(uuid);
        if (i != -1) {
            storage[i] = null;
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public int searchResume(String uuid) {
        for (int i = 0; i < size; i++) {
            String uuidInBase = storage[i].getUuid();

            if (uuidInBase.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] filledResume = Arrays.copyOf(storage, size);;
        return filledResume;
    }

    public int size() {
        return size;
    }
}

