class IRI < Formula
    desc "IOTA Reference Implementation"
    homepage "https://github.com/iotaledger/iri"
    url "https://github.com/iotaledger/iri/releases/download/1.1.4.2/iri-1.1.4.2.jar"
    sha256 "f441ca3c8992c4ed1e2c19c6b9080d22ad72fa056ab10dec0a4b7535833f1a47"
    //depends_on "mvn" => :build

    bottle :unneeded

    def install
        //prefix.install "iri-1.1.4.2.jar"
        bin.install "iri-1.1.4.2.jar"
    end

    /*
    def post_install
        system "lightproxy", "init"
    end

    test do
        system "#{bin}/lightproxy", "--version"
    end
    */
end
