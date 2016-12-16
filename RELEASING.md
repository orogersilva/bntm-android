Release Process
===============

Release
-------

1. Increment the version in `app/build.gradle` in case of any relevant update in the application,
    such as internal and/or public changes.

2. Update the `CHANGELOG.md` file describing the internal and/or public changes.

3. Commit the changes with `git commit -am "Preparing version X.Y.Z"`, replacing 'X.Y.Z' with the
    number of the new version.

4. Create a annotated tag with `git tag -a X.Y.Z -m "Version X.Y.Z"`, replacing 'X.Y.Z' with the
    number of the new version.

5. Push (`git push`) and push tag (`git push --tags`).

6. :shipit: