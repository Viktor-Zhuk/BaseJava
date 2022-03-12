package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = getIndex(uuidResume);
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + uuidResume + " successfully updated");
        } else {
            System.out.println("ERROR: resume " + uuidResume + " not found");
        }
    }

    public void save(Resume resume) {
        String uuidResume = resume.getUuid();
        int index = getIndex(uuidResume);
        if (index != -1) {
            System.out.println("ERROR: resume " + uuidResume + " already recorded");
        } else if (size == STORAGE_LIMIT){
            System.out.println("ERROR: the resume database is filled in");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume " + uuid + " not deleted");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}

